package com.example.tablas.service;

import com.example.tablas.models.Documento;
import java.util.List;
import java.util.Optional;

public interface DocumentoService {

    List<Documento> obtenerTodosLosDocumentos();
    Optional<Documento> obtenerDocumentoPorId(Long id);
    Documento crearDocumento(Documento documento);
    Optional<Documento> actualizarDocumento(Long id, Documento documento);
    boolean eliminarDocumento(Long id);
}
