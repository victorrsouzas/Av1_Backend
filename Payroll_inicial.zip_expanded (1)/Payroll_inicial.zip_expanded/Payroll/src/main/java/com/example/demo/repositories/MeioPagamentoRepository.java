package com.example.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.MeioPagamentos;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeioPagamentoRepository extends JpaRepository<MeioPagamentos, Long>{

    //List<MeioPagamentos> findByCodigoAndDescricao(String codigo, String descricao, Sort sort);
    Page<MeioPagamentos> findByCodigoAndDescricao(String codigo, String descricao, Pageable pageable);

    Page<MeioPagamentos> findByCodigoContaining(String codigo, Pageable pageable);

}
