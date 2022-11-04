package registraduria.Seguridad.seguridad.Controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import registraduria.Seguridad.seguridad.Modelos.Permiso;
import registraduria.Seguridad.seguridad.Modelos.PermisoRol;
import registraduria.Seguridad.seguridad.Modelos.Rol;
import registraduria.Seguridad.seguridad.Repositorios.RepositorioPermiso;
import registraduria.Seguridad.seguridad.Repositorios.RepositorioPermisoRol;
import registraduria.Seguridad.seguridad.Repositorios.RepositorioRol;

import java.util.List;

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

        PermisoRol permisoRol = new PermisoRol(rol, permisos);

        return miRepositorioPermisoRol.save(permisoRol);

    }

    @GetMapping
    public List<PermisoRol> buscarTodosPermisosRol() {
        return miRepositorioPermisoRol.findAll();

    }

    @GetMapping("{id}")
    public PermisoRol show(@PathVariable String id) {
        return miRepositorioPermisoRol
                .findById(id)
                .orElse(null);
    }


    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        miRepositorioPermisoRol.deleteById(id);
    }

    @PutMapping("{id}")
    public PermisoRol update(@PathVariable String id, @RequestBody PermisoRol infoPermisoRol) {

        PermisoRol permisoRolActual = this.miRepositorioPermisoRol
                .findById(id)
                .orElse(null);

        if (permisoRolActual != null) {
            permisoRolActual.setRol(infoPermisoRol.getRol());
            permisoRolActual.setPermiso(infoPermisoRol.getPermiso());
            return this.miRepositorioPermisoRol.save(permisoRolActual);
        } else {
            return null;
        }
    }
}

