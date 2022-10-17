package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.MeioPagamentos;

import java.util.Optional;

@Repository
public interface MeioPagamentoRepository extends JpaRepository<MeioPagamentos, Integer>{
    Optional<MeioPagamentos> findPaymentMethodByDescricao(String descricao);
	
}
