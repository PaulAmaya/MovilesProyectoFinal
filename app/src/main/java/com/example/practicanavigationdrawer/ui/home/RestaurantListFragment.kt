package com.example.practicanavigationdrawer.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicanavigationdrawer.databinding.FragmentHomeBinding
import com.example.practicanavigationdrawer.models.Restaurant
import com.example.practicanavigationdrawer.ui.adapters.RestaurantAdapter

class RestaurantListFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var restaurantViewModel: RestaurantViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        restaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)

        setupRecyclerView()
        observeViewModel()
        restaurantViewModel.fetchRestaurants()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerViewRestaurants.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeViewModel() {
        restaurantViewModel.restaurants.observe(viewLifecycleOwner) { restaurants ->
            binding.recyclerViewRestaurants.adapter = RestaurantAdapter(restaurants) { restaurant ->
                navigateToRestaurantDetails(restaurant.id)
            }
        }

        restaurantViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), "Error: $errorMessage", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToRestaurantDetails(restaurantId: Int) {
        val intent = Intent(requireContext(), RestaurantDetailActivity::class.java)
        intent.putExtra("restaurantId", restaurantId)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
