package com.MarketplaceBack.marketplaceBack.controller;

import com.google.api.gax.paging.Page;
import com.google.cloud.spring.storage.GoogleStorageResource;
import com.google.cloud.storage.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/un/gcs") // Se establece un prefijo para las rutas de este controlador
public class WebController {

    // Se obtiene el nombre del bucket desde application.properties
    @Value("${google.storage.bucket}")
    private String bucketName;

    // Se obtiene un archivo específico dentro del bucket
    @Value("gs://${google.storage.bucket}/my-file.txt")
    private Resource gcsFile;

    private final Storage storage;

    // Inyección de dependencia del servicio de Google Cloud Storage
    public WebController(Storage storage) {
        this.storage = storage;
    }

    /**
     * Endpoint para leer un archivo almacenado en Google Cloud Storage.
     * Si se proporciona un nombre de archivo como parámetro, se lee ese archivo,
     * de lo contrario, se usa el archivo predeterminado "my-file.txt".
     *
     * @param filename Nombre opcional del archivo a leer.
     * @return Contenido del archivo.
     */
    @GetMapping("/read")
    public String readGcsFile(@RequestParam("filename") Optional<String> filename) throws IOException {
        return StreamUtils.copyToString(
                filename.isPresent()
                        ? fetchResource(filename.get()).getInputStream() // Si hay un nombre de archivo, se obtiene ese archivo
                        : this.gcsFile.getInputStream(), // Si no, se usa el archivo por defecto
                Charset.defaultCharset()) + "\n";
    }

    /**
     * Endpoint para escribir o actualizar un archivo en Google Cloud Storage.
     * Si se proporciona un nombre de archivo, se escribe en ese archivo,
     * de lo contrario, se escribe en "my-file.txt".
     *
     * @param data Contenido que se desea escribir en el archivo.
     * @param filename Nombre opcional del archivo donde se escribirá.
     * @return Mensaje de confirmación.
     */
    @PostMapping("/write")
    public String writeGcs(@RequestBody String data, @RequestParam("filename") Optional<String> filename) throws IOException {
        return updateResource(
                filename.map(this::fetchResource).orElse((GoogleStorageResource) this.gcsFile), data);
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("folder") String folder) {
        try {
            // Validar si el archivo es una imagen
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Solo se permiten archivos de imagen.");
            }

            String originalFilename = file.getOriginalFilename().replaceAll("\\s+", "_");
            String blobName = folder + "/" + originalFilename;

            String gsUri = "gs://" + bucketName + "/" + blobName;
            GoogleStorageResource resource = new GoogleStorageResource(storage, gsUri, true);

            try (OutputStream os = resource.getOutputStream()) {
                os.write(file.getBytes());
            }

            // Hacer público
            BlobId blobId = BlobId.of(bucketName, blobName);
            storage.createAcl(blobId, Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

            String folderUrl = String.format("https://storage.googleapis.com/%s/%s/", bucketName, folder);
            return ResponseEntity.ok("Archivo subido correctamente");

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir el archivo: " + e.getMessage());
        }
    }



    // Listar archivos dentro de una carpeta
    @GetMapping("mostrarArchivos")
    public ResponseEntity<List<String>> listFiles(@RequestParam("folder") String folder) {
        List<String> fileUrls = new ArrayList<>();

        Page<Blob> blobs = storage.list(bucketName, Storage.BlobListOption.prefix(folder + "/"));
        for (Blob blob : blobs.iterateAll()) {
            if (!blob.isDirectory()) {
                String publicUrl = String.format("https://storage.googleapis.com/%s/%s", bucketName, blob.getName());
                fileUrls.add(publicUrl);
            }
        }

        return ResponseEntity.ok(fileUrls);
    }

    /**
     * Método interno para escribir datos en un archivo almacenado en Google Cloud Storage.
     *
     * @param resource Archivo de destino.
     * @param data Contenido a escribir.
     * @return Mensaje de éxito.
     */
    private String updateResource(Resource resource, String data) throws IOException {
        try (OutputStream os = ((WritableResource) resource).getOutputStream()) {
            os.write(data.getBytes());
        }
        return "El archivo ha sido actualizado.\n";
    }

    /**
     * Método interno para obtener un recurso de Google Cloud Storage con un nombre de archivo específico.
     *
     * @param filename Nombre del archivo dentro del bucket.
     * @return Recurso de Google Cloud Storage.
     */
    private GoogleStorageResource fetchResource(String filename) {
        return new GoogleStorageResource(
                this.storage, String.format("gs://%s/%s", this.bucketName,  filename));
    }
}
