package it.pc.test.WebSpringApp.repository.grid;

import it.pc.test.WebSpringApp.dto.grid.GridRequest;
import it.pc.test.WebSpringApp.dto.grid.SearchCriteria;
import it.pc.test.WebSpringApp.utils.LogUtils;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class GridSpecification<T> implements Specification<T> {

    private final SearchCriteria richiestaGriglia;

    public GridSpecification(SearchCriteria richiestaGriglia) {
        this.richiestaGriglia = richiestaGriglia;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Path<?> path = root.get(richiestaGriglia.getKeyColumn());

        // TODO Vedere se serve gestire i campi nested o se fare un oggetto apposito (flat)
        // Bozza campi nested
        /*if(richiestaGriglia.getKeyColumn().contains(".")){
            String[] split = richiestaGriglia.getKeyColumn().split("\\.");
            path = root.join(split[0]).get(split[1]);
        }else {
            path = root.get(richiestaGriglia.getKeyColumn());
        }*/

        Comparable valoreConvertito = castToType(path, richiestaGriglia.getValue().toString());

        switch (richiestaGriglia.getOperation()) {
            case EQUALS -> {
                return criteriaBuilder.equal(path, valoreConvertito);
            }
            case NOT_EQUALS -> {
                return criteriaBuilder.notEqual(path, valoreConvertito);
            }
            case GREATER_THAN -> {
                return criteriaBuilder.greaterThan((Path<Comparable>) path, valoreConvertito);
            }
            case LESS_THAN -> {
                return criteriaBuilder.lessThan((Path<Comparable>) path, valoreConvertito);
            }
            case LIKE -> { // Contains, ricerca generica
                if (path.getJavaType().equals(String.class)) {
                    // Ricerca case-insensitive (lowerCase) per le stringhe
                    return criteriaBuilder.like(criteriaBuilder.lower(path.as(String.class)),
                            "%" + richiestaGriglia.getValue().toString().toLowerCase() + "%");
                } else {
                    // Se è numero, data, boolean, etc... converto a stringa senza fare .lower
                    Expression<String> castToString = criteriaBuilder.concat(path.as(String.class), ""); // conversione numero -> string (postgres)
                    return criteriaBuilder.like(castToString, "%" + richiestaGriglia.getValue().toString() + "%");
                }
            }
            default -> {
                LogUtils.log.error("Operazione griglia non prevista: {}", richiestaGriglia.getOperation());
                return null;
            }
        }

    }

    private Comparable castToType(Path<?> path, String value) {

        Class<?> javaType = path.getJavaType();

        if (javaType.equals(Integer.class) || javaType.equals(int.class)) {
            return Integer.valueOf(value);
        } else if (javaType.equals(Long.class) || javaType.equals(long.class)) {
            return Long.valueOf(value);
        } else if (javaType.equals(BigDecimal.class)) {
            return new BigDecimal(value);
        } else if (javaType.equals(BigInteger.class)) {
            return new BigInteger(value);
        } else if (javaType.equals(LocalDate.class)) {
            return LocalDate.parse(value);
        } else if (javaType.equals(LocalDateTime.class)) {
            return LocalDateTime.parse(value);
        } else if (javaType.equals(Boolean.class) || javaType.equals(boolean.class)) {
            return Boolean.valueOf(value);
        } else {
            LogUtils.log.warn("Tipo valore non previsto, Valore: {} Classe: {}", value, value.getClass());
            return value;
        }
    }

    /**
     * Concatena le varie Specification per creare la query con i filtri richiesti dalla griglia (WHERE campo1 = x AND campo2 = Y, etc...).
     *
     * @param richiestaGriglia dto ricevuto dal FE con i vari filtri da applicare alla griglia
     * @param <T>              DTO
     * @return Specification contenente tutti i filtri da passare alla findAll di Jpa
     */
    public static <T> Specification<T> buildGridQuery(GridRequest richiestaGriglia) {
        Specification<T> spec = Specification.where(null);
        if (richiestaGriglia.getFilters() != null && !richiestaGriglia.getFilters().isEmpty()) {
            for (SearchCriteria criteria : richiestaGriglia.getFilters()) {
                // Concateno i vari filtri ricevuti (uso AND di default)
                spec = spec.and(new GridSpecification<T>(criteria));
            }
        }
        return spec;
    }

    /**
     * Applica i sort ricevuti dalla griglia e restituisce un oggetto da passare alla PageRequest
     */
    public static Sort buildSort(GridRequest richiestaGriglia) {
        Sort sortOrder = Sort.unsorted();
        if (richiestaGriglia.getSorts() != null && !richiestaGriglia.getSorts().isEmpty()) {
            List<Sort.Order> orders = richiestaGriglia.getSorts().stream()
                    .map(sortRequest -> sortRequest.isDesc() ?
                            Sort.Order.desc(sortRequest.getKeyColumn()) :
                            Sort.Order.asc(sortRequest.getKeyColumn())).toList();
            sortOrder = Sort.by(orders);
        }
        return sortOrder;
    }
}
