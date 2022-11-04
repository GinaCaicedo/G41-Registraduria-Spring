package registraduria.Seguridad.seguridad.Modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()
public class Rol extends Permiso {
    @Id
    private String _id;

    private String nombre;
    private String descripcion;

    public Rol(String url, String metodo, String nombre, String descripcion) {
        super(url, metodo);
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}

