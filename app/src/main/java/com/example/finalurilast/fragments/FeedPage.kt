package com.example.finalurilast.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalurilast.R
import com.example.finalurilast.recycler.News
import com.example.finalurilast.recycler.RecyclerAdapter

class FeedPage: Fragment(R.layout.feed_layout) {
    private lateinit var  recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<News>
    lateinit var imageId:Array<Int>
    lateinit var heading:Array<String>
    lateinit var frags:Array<Fragment>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageId = arrayOf(
            R.drawable.img_1,
            R.drawable.img_10,
            R.drawable.img_3,
            R.drawable.img_4,
            R.drawable.img_5,
            R.drawable.img_6,
            R.drawable.img_7,
            R.drawable.img_8,
            R.drawable.img_9,
            R.drawable.img_2
        )
        heading = arrayOf(
            "Jujutsu Kaisen 0 Animated By MAPPA, Story About Yuta",
            "Puss In Boots Back at New And Very Interesting Adventure",
            "Avengers Fighting For The Safety of Everyone Now Alot More At Stake!",
            "An Adventure Of Giant Green Ogre",
            "Little Girl Setting an Adventure In Abandoned Amusement Park",
            "Relationship Of a Girl And Forest Spirit",
            "Watch as Mr.Trueman's Reality Entirely Crumbles",
            "Main Demon Slayer Gang On an Adventure With Hashira Named Rengoku",
            "Watch as New Villain Called Joker Wracks Havock in The City of Gotham",
            "Story of Two People Switching Bodies Every so Often"
        )
        frags = arrayOf(
            Jujutsi(),
            PussinBoots(),
            SecretWars(),
            Shrek(),
            SpiritedAway(),
            LighofTheForest(),
            TruemanShow(),
            DemonSlayer(),
            Jokere()
        )

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<News>()

        getUserData()

    }
    private fun getUserData(){
        for(i in imageId.indices){
            val news = News(imageId[i],heading[i])
            newArrayList.add(news)
        }
        var adapter = RecyclerAdapter(newArrayList)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object :RecyclerAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                for (j in frags.indices){
                    if(position == j){
                        val yes = frags[j]
                        val fragmentManager = parentFragmentManager
                        fragmentManager.commitNow {
                            setReorderingAllowed(true)
                            replace(R.id.nav_host_fragment_sign,yes)
                        }
                    }
                }


                }


        })
    }
}


