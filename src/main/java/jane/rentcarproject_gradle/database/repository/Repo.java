package jane.rentcarproject_gradle.database.repository;

import jane.rentcarproject_gradle.database.entity.BaseEntity;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Repo<K extends Serializable, E extends BaseEntity<K>> {
    E save(E entity);

    void delete(E entity);

    E update(E entity);

    default Optional<E> findById(K id) {
        return findById(id, Collections.emptyMap());
    }

    Optional<E> findById(K id, Map<String, Object> properties);

    List<E> findAll();
}
