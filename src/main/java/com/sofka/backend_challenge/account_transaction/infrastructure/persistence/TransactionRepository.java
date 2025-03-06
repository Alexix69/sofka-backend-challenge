package com.sofka.backend_challenge.account_transaction.infrastructure.persistence;

import com.sofka.backend_challenge.account_transaction.domain.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByAccountIdAndTransactionDateBetween(Long accountId, LocalDateTime startDate, LocalDateTime endDate);

    List<TransactionEntity> findByAccountId(Long accountId);

    @Query("SELECT t FROM TransactionEntity t " +
            "WHERE t.account.id IN (SELECT a.id FROM AccountEntity a WHERE a.client.id = :clientId) " +
            "AND t.transactionDate BETWEEN :startDate AND :endDate")
    List<TransactionEntity> findByClientTransactions(
            @Param("clientId") Long clientId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
