package com.example.PokemonAPI.service;

import com.example.PokemonAPI.model.Pokemon;
import com.example.PokemonAPI.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public List<Pokemon> obtenerTodos(){
        return pokemonRepository.findAll();
    }

    public Pokemon obtenerPorId(Long id){
        return pokemonRepository.findById(id).orElse(null);
    }

    public Pokemon crear(Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }

    public Optional<Pokemon> actualizar(Long id,Pokemon pokemon) {
        return pokemonRepository.findById(id).map(pokemonExist-> {
            pokemonExist.setAtaque(pokemon.getAtaque());
            pokemonExist.setDefensa(pokemon.getDefensa());
            pokemonExist.setDescripcion(pokemon.getDescripcion());
            pokemonExist.setImagenUrl(pokemon.getImagenUrl());
            pokemonExist.setNivel(pokemon.getNivel());
            pokemonExist.setNombre(pokemon.getNombre());
            pokemonExist.setTipo(pokemon.getTipo());
            return pokemonRepository.save(pokemonExist);
        });
    }

    public Optional<Pokemon> actualizarParcialmente(Long id,Pokemon pokemon) {
        return pokemonRepository.findById(id).map(pokemonExist-> {
            if(pokemon.getAtaque() !=null)pokemonExist.setAtaque(pokemon.getAtaque());
            if(pokemon.getDefensa() !=null)pokemonExist.setDefensa(pokemon.getDefensa());
            if(pokemon.getDescripcion() !=null)pokemonExist.setDescripcion(pokemon.getDescripcion());
            if(pokemon.getImagenUrl() !=null)pokemonExist.setImagenUrl(pokemon.getImagenUrl());
            if(pokemon.getNivel() !=null)pokemonExist.setNivel(pokemon.getNivel());
            if(pokemon.getNombre() !=null)pokemonExist.setNombre(pokemon.getNombre());
            if(pokemon.getTipo() !=null)pokemonExist.setTipo(pokemon.getTipo());
            return pokemonRepository.save(pokemonExist);
        });
    }

    public void eliminarPokemon(Long id){
        pokemonRepository.deleteById(id);
    }
}
