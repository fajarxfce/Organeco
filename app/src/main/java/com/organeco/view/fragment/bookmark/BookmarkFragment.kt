package com.organeco.view.fragment.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.organeco.model.local.adapter.BookmarkAdapter
import com.organeco.model.local.entity.Recommendation
import com.organeco.viewmodel.RecommendationViewModel
import com.organeco.databinding.FragmentBookmarkBinding
import com.organeco.view.activity.result.ResultActivity
import com.organeco.viewmodel.RecommendationViewModelFactory

class BookmarkFragment : Fragment() {

    private var tabName: String? = null
    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var adapter: BookmarkAdapter
    private val recommendationViewModel: RecommendationViewModel by viewModels {
        RecommendationViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listBookmark()
        
    }

private fun listBookmark() {
    recommendationViewModel.getAllRecommendation().observe(viewLifecycleOwner) {
        checkData(it)
        adapter = BookmarkAdapter(it)
        binding.rvBookmark.adapter = adapter
        binding.rvBookmark.layoutManager = LinearLayoutManager(requireActivity())
        adapter.setOnItemClickCallback(object : BookmarkAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Recommendation) {
                Intent(requireActivity(), ResultActivity::class.java).also {

                }
            }
        })
    }
}

    private fun checkData(list: List<Recommendation>){
        if (list.isEmpty()) {
            binding.apply {
                ivNotFound.visibility = View.VISIBLE
                tvNotFound.visibility = View.VISIBLE
            }


        }
    }
}