package registraduria.Seguridad.seguridad.Modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document()
@Data
public class PermisoRol {

    @Id
    private String _id;
    //hacen referencia a otra base de datos
    @DBRef
    private String rol;
    @DBRef
    private Rol permiso;

    public PermisoRol(String rol, Rol permiso) {
        this.rol = rol;
        this.permiso = permiso;
    }
}
