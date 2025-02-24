package com.MarketplaceBack.marketplaceBack.repository;

import com.MarketplaceBack.marketplaceBack.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
}
