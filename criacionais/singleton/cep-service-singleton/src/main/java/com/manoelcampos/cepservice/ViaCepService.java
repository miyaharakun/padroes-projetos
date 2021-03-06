package com.manoelcampos.cepservice;

import javax.enterprise.inject.Alternative;

/**
 * Acesso à API REST to serviço <a href="https://viacep.com.br">ViaCEP</a>.
 * Exemplo de requisição: <a href="https://viacep.com.br/ws/01001000/json/">https://viacep.com.br/ws/01001000/json/</a>
 *
 * @author Manoel Campos da Silva Filho
 */
@Alternative
public final class ViaCepService extends AbstractCepService {
    private static final String DOMINIO = "https://viacep.com.br/";

    /**
     * Define um construtor padrão que não aceita parâmetros.
     * Ele apenas chama o construtor protegido na superclasse,
     * passando o domínio do serviço a ser acessado.
     * O construtor da superclasse não é incluído aqui pois
     * não queremos que quem for utilizar tal classe,
     * possa chamar aquele construtor.
     * Se tal construtor fosse adicionado,
     * o usuário da classe poderia instanciá-la, indicando um domínio diferente
     * para o serviço. No entanto, o domínio e fixo, logo, não deve ser alterado.
     */
    public ViaCepService(){
        super(DOMINIO);
    }

    @Override
    protected String buildPath(final String cep) {
        return "ws/" + cep + "/json";
    }
}
