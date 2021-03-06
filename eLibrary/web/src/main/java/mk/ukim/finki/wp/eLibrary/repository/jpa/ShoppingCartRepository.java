package mk.ukim.finki.wp.eLibrary.repository.jpa;

import mk.ukim.finki.wp.eLibrary.model.ShoppingCart;
import mk.ukim.finki.wp.eLibrary.model.User;
import mk.ukim.finki.wp.eLibrary.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);

    void deleteById(Long id);

    void deleteAll();

}
