package ru.gb.thymeleafprepare.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.thymeleafprepare.entity.Cart;
import ru.gb.thymeleafprepare.entity.Product;
import ru.gb.thymeleafprepare.entity.enums.Status;

import java.util.List;
import java.util.Optional;

public interface CartDao extends JpaRepository<Cart, Long> {
    List<Cart> findAllByStatus(Status status);
    List<Cart> findAllByStatus(Status status, Pageable pageable);
    List<Cart> findAllByStatus(Status status, Sort sort);

    Optional<Cart> findByTitle(String title);
    List<Cart> findAllByTitleContaining(String title);

}
