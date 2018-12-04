package chc.tfm.udt.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "equipaciones")
public class EquipacionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Double precio;
    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    //Metodo que usaremos para persistir la fecha justn en el momento de crear la claes
    @PrePersist
    public void prePersist(){
        createAt = new Date();
    }
}
