package registraduria.Seguridad.seguridad.Controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import registraduria.Seguridad.seguridad.Modelos.Permiso;
import registraduria.Seguridad.seguridad.Modelos.PermisoRol;
import registraduria.Seguridad.seguridad.Modelos.Rol;
import registraduria.Seguridad.seguridad.Repositorios.RepositorioPermiso;
import registraduria.Seguridad.seguridad.Repositorios.RepositorioPermisoRol;
import registraduria.Seguridad.seguridad.Repositorios.RepositorioRol;

@RestController
@CrossOrigin
@RequestMapping("/permiso-rol")
public class ControladorPermisoRol {
    @Autowired
    private RepositorioPermisoRol miRepositorioPermisoRol;
    @Autowired
    private RepositorioRol miRepositorioRol;
    @Autowired
    private RepositorioPermiso miRepositorioPermiso;

    @PostMapping("/rol/{idRol}/permiso/{idPermiso}")
    public PermisoRol crearPermisosRol(@PathVariable String idRol, @PathVariable String idPermiso) {

        Rol rol = miRepositorioRol
                .findById(idRol)
                .orElseThrow(RuntimeException::new);

        Permiso permisos = miRepositorioRol
                .findById(idPermiso)
                .orElseThrow(RuntimeException::new);

        PermisoRol PermisoRol = new PermisoRol(rol,permisos);

        return miRepositorioPermisoRol.save(PermisoRol);

    }
}
