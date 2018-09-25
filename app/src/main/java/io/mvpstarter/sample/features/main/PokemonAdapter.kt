package io.mvpstarter.sample.features.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import io.mvpstarter.sample.R
import javax.inject.Inject

class PokemonAdapter @Inject
constructor() : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private var pokemonsList: List<String>
    private var clickListener: ClickListener? = null

    init {
        pokemonsList = emptyList<String>()
    }

    fun setPokemon(pokemons: List<String>) {
        pokemonsList = pokemons
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonsList[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int {
        return pokemonsList.size
    }

    interface ClickListener {
        fun onPokemonClick(pokemon: String)
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var selectedPokemon: String

        @BindView(R.id.pokemon_name)
        @JvmField var pokemonName: TextView? = null

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener {
                clickListener?.onPokemonClick(selectedPokemon)
            }
        }

        fun bind(pokemon: String) {
            selectedPokemon = pokemon
            pokemonName?.text = String.format("%s%s", pokemon.substring(0, 1).toUpperCase(),
                    pokemon.substring(1))
        }
    }

}