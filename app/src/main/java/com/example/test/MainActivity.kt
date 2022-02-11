package com.example.test

import android.content.Context
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_details.view.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.nutrition_layout.view.*
import kotlinx.android.synthetic.main.recycler_list_products.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav_graph)
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.toolbar_color))
    }

    private fun TextView.bold(text: String) {
        val builder = SpannableStringBuilder(text)

        builder.setSpan(StyleSpan(Typeface.BOLD), 0, text.indexOf(':') + 1, 0)

        setText(builder)
    }
}

class ProductsListFragment  : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.recycler_list_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listOfTitles = listOf(
            "title1",
            "title2",
            "title3"
        )

        val recyclerView = list
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = MyAdapter(listOfTitles)

        button_to_product.setOnClickListener {
            findNavController().navigate(

                ProductsListFragmentDirections.actionProductsListFragmentToProductDetailsFragment(
                )
            )
        }
    }
}

class ProductDetailsFragment  : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load("https://fr.openfoodfacts.org/images/products/308/368/048/1977/front_fr.3.full.jpg").into(view.productImg)

        view.brand.text = product.brand
        view.brand.text = product.brand
        view.name.text = product.name
        view.barCode.text = "Code barres : " + product.barCode
        view.quantity.text = "Quantité :" + product.quantity
        view.countries.text = "Vendu en :" + product.countries
        view.ingredients.text = "Ingrédients :" + product.ingredients
        view.allergens.text = "Substances allergènes :" + product.allergens
        view.additives.text = "Additifs :" + product.additives

        // barCode.bold(barCode.text as String)
        // quantity.bold(quantity.text as String)
        //countries.bold(countries.text as String)
        // ingredients.bold(ingredients.text as String)
        // allergens.bold(allergens.text as String)
        // additives.bold(additives.text as String)
    }
}

class ProductDetailsSummaryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.product_details_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

val salt = NutritionFactsItem("g", "100", "0,75")
val saturated_fatty_acids = NutritionFactsItem("g", "100", "0,1")
val fat = NutritionFactsItem("g", "100", "0,8")
val sugar = NutritionFactsItem("g", "100", "5,2")
val object1Facts = NutritionFacts(salt, fat, saturated_fatty_acids, salt, sugar, salt, salt, salt, salt)

class ProductDetailsNutritionFragment  : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.nutrition_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val salt = NutritionFactsItem("g", "100", "0,75")
        val saturatedFattyAcids = NutritionFactsItem("g", "100", "0,1")
        val fat = NutritionFactsItem("g", "100", "0,8")
        val sugar = NutritionFactsItem("g", "100", "5,2")
        val object1Facts = NutritionFacts(salt, fat, saturatedFattyAcids, salt, sugar, salt, salt, salt, salt)

        view.fat.text =  product.nutritionFacts.fat.tenQuantity + product.nutritionFacts.fat.unite +" de Matière grasses / Lipides "
        view.salt.text =  product.nutritionFacts.salt.tenQuantity + product.nutritionFacts.salt.unite + " sel"
        view.sugar.text =  product.nutritionFacts.sugar.tenQuantity + product.nutritionFacts.sugar.unite + " sucres"
        view.saturated_fatty_acids.text =  product.nutritionFacts.saturated_fatty_acids.tenQuantity + product.nutritionFacts.saturated_fatty_acids.unite + " Acides gras saturés"
    }
}

class Product(
    val name: String, val brand: String, val barCode: String, val nutriScore: String, val img: String,
    val quantity: String, val countries: String, val ingredients: String,val allergens: String,
    val additives: String, val nutritionFacts: NutritionFacts
)

class MyAdapter(private val titles: List<String>) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindTitle(titles[position])
    }

    override fun getItemCount(): Int {
        return titles.size
    }

}

// Le contenu d'une cellule
class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val titleTextView: TextView = v.food_title

    fun bindTitle(day: String) {
        titleTextView.text = day
    }

}

private fun TextView.bold(text: String) {
    val builder = SpannableStringBuilder(text)

    builder.setSpan(StyleSpan(Typeface.BOLD), 0, text.indexOf(':') + 1, 0)

    setText(builder)
}


var product = Product("Petits pois et carottes", "Cassegrain", "3083680085304",
    "", "https://static.openfoodfacts.org/images/products/308/368/008/5304/front_fr.7.400.jpg",
    "10", "France, Japon, Italie", "Petits pois 66% ,eau,garniture 2,8% (salade, oignon grelot,sucre, sel, arôme naturel)",
    "Aucune","Aucun", object1Facts
)


class NutritionFactsItem (
    val unite: String,
    val portionQuantity: String,
    val tenQuantity: String
)

class NutritionFacts  (
    val energy: NutritionFactsItem,
    val fat: NutritionFactsItem,
    val saturated_fatty_acids: NutritionFactsItem,
    val carbohydrates: NutritionFactsItem,
    val sugar: NutritionFactsItem,
    var fibers: NutritionFactsItem,
    var protein: NutritionFactsItem,
    var salt: NutritionFactsItem,
    var sodium: NutritionFactsItem,
)




