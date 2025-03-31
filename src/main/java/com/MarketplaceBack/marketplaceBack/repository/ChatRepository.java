package com.MarketplaceBack.marketplaceBack.repository;

import com.MarketplaceBack.marketplaceBack.models.Chat;
import com.MarketplaceBack.marketplaceBack.models.DTO.ChatList;
import com.MarketplaceBack.marketplaceBack.models.DTO.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    //Consulta que optiene el ultimo mensaje todas la conversaciones que tiene el usurario con diferentes personas
    @Query(value =
            "SELECT \n" +
                    "    CASE \n" +
                    "        WHEN c.idUsuario1 = :usuarioID THEN u2.nickname \n" +
                    "        ELSE u1.nickname \n" +
                    "    END AS nickname2,\n" +
                    "    c.mensaje,\n" +
                    "    c.fecha,\n" +
                    "    CASE \n" +
                    "        WHEN c.idUsuario1 = :usuarioID THEN c.idUsuario2\n" +
                    "        ELSE c.idUsuario1\n" +
                    "    END AS otroUsuarioID,\n" +
                    "    CASE \n" +
                    "        WHEN c.idUsuario1 = :usuarioID THEN u1.nickname \n" +
                    "        ELSE u2.nickname \n" +
                    "    END AS enviadoPor\n" +
                    "FROM chat c\n" +
                    "JOIN usuarios u1 ON c.idUsuario1 = u1.idUsuario\n" +
                    "JOIN usuarios u2 ON c.idUsuario2 = u2.idUsuario\n" +
                    "WHERE (c.idUsuario1 = :usuarioID OR c.idUsuario2 = :usuarioID)\n" +
                    "  AND c.id = (\n" +
                    "      SELECT MAX(c2.id)\n" +
                    "      FROM chat c2\n" +
                    "      WHERE (c2.idUsuario1 = :usuarioID OR c2.idUsuario2 = :usuarioID)\n" +
                    "        AND (\n" +
                    "            (c2.idUsuario1 = c.idUsuario1 AND c2.idUsuario2 = c.idUsuario2)\n" +
                    "            OR (c2.idUsuario1 = c.idUsuario2 AND c2.idUsuario2 = c.idUsuario1)\n" +
                    "        )\n" +
                    "  )\n" +
                    "ORDER BY c.fecha DESC;",
            nativeQuery = true)
    Optional<List<ChatList>> findUltimosMensajesPorUsuario(@Param("usuarioID") Integer usuarioID);
    //consulta que optiene todos los mensaje que tiene el usuario
    @Query(value = "SELECT \n" +
            "    c.mensaje,\n" +
            "    c.fecha,\n" +
            "    \n" +
            "    CASE \n" +
            "        WHEN c.idUsuario1 = :idUsuario THEN (SELECT nickname FROM usuarios WHERE idUsuario = :idUsuario LIMIT 1)\n" +
            "        ELSE :Nickname2\n" +
            "    END AS remitenteNickname,\n" +
            "    CASE \n" +
            "        WHEN c.idUsuario1 != :idUsuario THEN (SELECT nickname FROM usuarios WHERE idUsuario = :idUsuario LIMIT 1)\n" +
            "        ELSE :Nickname2\n" +
            "    END AS receptorNickname\n" +
            "FROM chat c\n" +
            "WHERE \n" +
            "    (c.idUsuario1 = :idUsuario \n" +
            "     AND c.idUsuario2 = (SELECT idUsuario FROM usuarios WHERE nickname = :Nickname2 LIMIT 1))\n" +
            "    OR\n" +
            "    (c.idUsuario1 = (SELECT idUsuario FROM usuarios WHERE nickname = :Nickname2 LIMIT 1)\n" +
            "     AND c.idUsuario2 = :idUsuario)\n" +
            "ORDER BY c.fecha ASC", nativeQuery = true)
    Optional<List<ChatMessage>> findConversacion(Integer idUsuario, String Nickname2);

}
