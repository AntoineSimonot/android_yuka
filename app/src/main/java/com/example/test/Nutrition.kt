package com.example.test

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.nutrition_layout.*
import kotlinx.android.synthetic.main.recycler_list_products.*

class Nutrition : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nutrition_layout)
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.toolbar_color))

        var product = Product("Petits pois et carottes", "Cassegrain", "3083680085304",
            "", "https://static.openfoodfacts.org/images/products/308/368/008/5304/front_fr.7.400.jpg",
            "10", "France, Japon, Italie", "Petits pois 66% ,eau,garniture 2,8% (salade, oignon grelot,sucre, sel, arôme naturel)",
            "Aucune","Aucun", object1Facts
        )

        fat.text =  product.nutritionFacts.fat.tenQuantity + product.nutritionFacts.fat.unite +" de Matière grasses / Lipides "
        salt.text =  product.nutritionFacts.salt.tenQuantity + product.nutritionFacts.salt.unite + " sel"
        sugar.text =  product.nutritionFacts.sugar.tenQuantity + product.nutritionFacts.sugar.unite + " sucres"
        saturated_fatty_acids.text =  product.nutritionFacts.saturated_fatty_acids.tenQuantity + product.nutritionFacts.saturated_fatty_acids.unite + " Acides gras saturés"


    }


}


