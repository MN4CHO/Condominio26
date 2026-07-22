package fis.dsw.sgc.finanzas.service;

import fis.dsw.sgc.finanzas.dto.NuevaDeudaDTO;

import java.time.LocalDate;


public interface IFachadaParaReservas {

    void registrarDeuda(NuevaDeudaDTO nuevaDeuda);
}
