package registraduria.Seguridad.seguridad.Controladores;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import registraduria.Seguridad.seguridad.Modelos.Rol;
import registraduria.Seguridad.seguridad.Modelos.Usuario;
import registraduria.Seguridad.seguridad.Repositorios.RepositorioRol;
import registraduria.Seguridad.seguridad.Repositorios.RepositorioUsuario;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;



@CrossOrigin
@RestController
@RequestMapping("/usuarios")

public class ControladorUsuario {
    @Autowired
    private RepositorioUsuario miRepositorioUsuario;

    @Autowired
    private RepositorioRol miRepositorioRol;

    @GetMapping("")
    public List<Usuario> indexusuario() {
        return miRepositorioUsuario.findAll();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario create (@RequestBody Usuario infoUsuario) {

        infoUsuario.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
        return miRepositorioUsuario.save(infoUsuario);
    }

    @GetMapping("{id}")
    public Usuario show(@PathVariable String id) {
        return miRepositorioUsuario
                .findById(id)
                .orElse(new Usuario("", "", ""));

    }

    @PutMapping("{id}")
    public Usuario update(@PathVariable String id, @RequestBody Usuario
            infoUsuario) {
        Usuario usuarioActual = miRepositorioUsuario
                .findById(id)
                .orElse(null);
        if (usuarioActual != null) {
            usuarioActual.setSeudonimo(infoUsuario.getSeudonimo());
            usuarioActual.setCorreo(infoUsuario.getCorreo());
            usuarioActual.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
            return miRepositorioUsuario.save(usuarioActual);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Usuario usuarioActual = this.miRepositorioUsuario
                .findById(id)
                .orElse(null);
        if (usuarioActual != null) {
            this.miRepositorioUsuario.delete(usuarioActual);
        }
    }

    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

//
//    /* API GATEWAY- queries- algo pasa que no funcionA*/
//    @PostMapping("validar-usuario")
//    public Usuario validarUsuario(@RequestBody Usuario infoUsuario,HttpServletResponse response) throws IOException {
//        log.info("Validando el usuario, request body: {}", infoUsuario);
//
//        //busca el usuario  por email
//        Usuario usuarioActual =miRepositorioUsuario.findByEmail(infoUsuario.getCorreo());
//
//        if (usuarioActual != null) {
//            log.info("Validando el usuario 2, request body: {}", infoUsuario);
//            String contrasenaUsuario = convertirSHA256(infoUsuario.getContrasena());
//            String contrasenaBaseDatos = infoUsuario.getContrasena();
//
//            if (contrasenaUsuario.equals(contrasenaBaseDatos)) {
//                log.info("Validando el usuario 3, request body: {}", infoUsuario);
//                usuarioActual.setContrasena(" ");
//                return usuarioActual;
//            } else {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//                return null;
//            }
//        } else {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            return null;
//        }
//    }


    @PostMapping("validar-usuario")
    public Usuario validarUsuario(@RequestBody Usuario infoUsuario,HttpServletResponse response) throws IOException {
        Usuario usuarioActual = miRepositorioUsuario.findByEmail(infoUsuario.getCorreo());

        if (usuarioActual != null && usuarioActual.getContrasena().equals(convertirSHA256(infoUsuario.getContrasena()))){
            usuarioActual.setContrasena("");
            return usuarioActual;
        }else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }


        //Asociar usuario rol(existen en la base de datos)

    @PutMapping("{infoUsuario}/rol/{InfoRol}")
    public Usuario asignarUsuario(@PathVariable String infoUsuario, @PathVariable String InfoRol) {

        Usuario usuario = miRepositorioUsuario
                .findById(infoUsuario)
                .orElse(null);

        Rol rol = miRepositorioRol
                .findById(InfoRol)
                .orElse(null);

        if (usuario != null && rol != null) {
            usuario.setRol(rol);
            //actualizo db
            return this.miRepositorioUsuario.save(usuario);

        } else {
            return null;
        }
    }

}
