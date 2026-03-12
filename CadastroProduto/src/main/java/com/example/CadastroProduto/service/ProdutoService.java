package com.example.CadastroProduto.service;

import com.example.CadastroProduto.model.ProdutoModel;
import com.example.CadastroProduto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoModel> listarTodos() {
        return repository.findAll();
    }

    public Optional<ProdutoModel> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public ProdutoModel salvar(ProdutoModel produto) {
        return repository.save(produto);
    }

    public ProdutoModel atualizar(Long id, ProdutoModel produtoAtualizado) {
        Optional<ProdutoModel> produtoExistente = repository.findById(id);

        if (produtoExistente.isPresent()) {
            ProdutoModel produto = produtoExistente.get();
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setQuantidadeEmEstoque(produtoAtualizado.getQuantidadeEmEstoque());
            produto.setStatus(produtoAtualizado.getStatus());

            return repository.save(produto);
        } else {
            throw new RuntimeException("Produto não encontrado com id " + id);
        }
    }

    public void deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Produto não encontrado com id " + id);
        }
    }
}
