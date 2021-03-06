package com.manoelcampos.exportador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe abstrata que fornece uma implementação base para as subclasses
 * que definem formatos específicos de exportação de dados.
 *
 * @author Manoel Campos da Silva Filho
 */
public abstract class AbstractExportadorListaProduto implements ExportadorListaProduto {
    /**
     * Lista de produtos a serem exportados para um formato define pelas subclasses.
     */
    private List<Produto> listaProdutos;

    /**
     * Colunas a serem exibidas na tabela gerada no processo de exportação.
     */
    protected static final List<String> TITULOS_COLUNAS = Arrays.asList("ID", "Descrição", "Marca", "Modelo", "Estoque");

    @Override
    public final String exportar(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
        final StringBuilder sb = new StringBuilder();
        sb.append(abrirTabela());

        gerarColunasLinha(sb, TITULOS_COLUNAS);
        sb.append(fecharLinhaTitulos());
        gerarLinhasProdutos(sb);

        sb.append(fecharTabela());
        return sb.toString();
    }

    /**
     * Gera o texto representando todas as linhas de uma tabela (em um formato definido pelas subclasses)
     * contendo os dados dos produtos na {@link #listaProdutos}.
     *
     * @param sb {@link StringBuilder} onde o texto gerado será adicionado
     */
    private void gerarLinhasProdutos(StringBuilder sb) {
        for (Produto produto : listaProdutos) {
            List<String> valoresCamposProduto = new ArrayList<>();
            valoresCamposProduto.add(String.valueOf(produto.getId()));
            valoresCamposProduto.add(produto.getDescricao());
            valoresCamposProduto.add(produto.getMarca());
            valoresCamposProduto.add(produto.getModelo());
            valoresCamposProduto.add(String.valueOf(produto.getEstoque()));
            gerarColunasLinha(sb, valoresCamposProduto);
        }
    }

    /**
     * Gera o texto representando uma única linha de uma tabela (em um formato definido pelas subclasses).
     *
     * @param sb {@link StringBuilder} onde o texto gerado será adicionado
     * @param valores valores a serem exibidos nas colunas, que podem ser:
     *                (i) os títulos das colunas (caso esteja sendo gerada a linha de cabeçalho da tabela) ou
     *                (ii) os valores de uma linha da tabela (caso esteja sendo gerado uma linha de conteúdo da tabela).
     *                Neste último caso, tal parâmetro deve conter os valores dos atributos de um objeto da {@link #listaProdutos}.
     */
    private void gerarColunasLinha(StringBuilder sb, List<String> valores) {
        sb.append(abrirLinha());
        for (String valor : valores) {
            sb.append(abrirColuna(valor))
              .append(fecharColuna());
        }
        sb.append(fecharLinha());
    }
}
