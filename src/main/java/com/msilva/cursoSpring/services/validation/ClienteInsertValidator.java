package com.msilva.cursoSpring.services.validation;

import com.msilva.cursoSpring.domain.enums.TipoCliente;
import com.msilva.cursoSpring.dto.NovoClienteDTO;
import com.msilva.cursoSpring.resources.exceptions.FieldMessage;
import com.msilva.cursoSpring.services.validation.utils.BR;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Classe responsável por validar os dados de Cliente.
 *
 * @author Mateus
 */
public class ClienteInsertValidator
        implements ConstraintValidator<ClienteInsert, NovoClienteDTO> {

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(NovoClienteDTO objDto,
            ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCodigo())
                && !BR.isValidCPF(objDto.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", "CPF Inválido!"));

        } else if (objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCodigo())
                && !BR.isValidCNPJ(objDto.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", "CNPJ Inválido!"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
