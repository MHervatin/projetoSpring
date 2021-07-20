package com.msilva.cursoSpring.services.validation;

import com.msilva.cursoSpring.domain.Cliente;
import com.msilva.cursoSpring.domain.enums.TipoCliente;
import com.msilva.cursoSpring.dto.ClienteDTO;
import com.msilva.cursoSpring.repositories.ClienteRepository;
import com.msilva.cursoSpring.resources.exceptions.FieldMessage;
import com.msilva.cursoSpring.services.validation.utils.BR;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

/**
 * Classe responsável por validar os dados de Cliente.
 *
 * @author Mateus
 */
public class ClienteUpdateValidator
        implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    /**
     * Provê a instância do repositório de Cliente.
     */
    @Autowired
    ClienteRepository ClienteRepository;

    /**
     * Provê serviços de requisão de Servlet HTTP.
     */
    @Autowired
    private HttpServletRequest resquestHttp;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto,
            ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Map<String, String> mapNomePorValorPropriedades
                = (Map<String, String>) resquestHttp.getAttribute(
                        HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        long uriId = Long.parseLong(mapNomePorValorPropriedades.get("id"));

        Cliente clientePorEmail = ClienteRepository.findByEmail(objDto.getEmail());

        if (clientePorEmail != null
                && !clientePorEmail.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "E-mail já existente!"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
