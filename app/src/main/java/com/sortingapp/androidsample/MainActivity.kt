package com.sortingapp.androidsample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sortingapp.androidsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun  getNumbers(): IntArray {
        var numbers = binding.numbers.text.toString()
        numbers = numbers.replace(" ", ",")
        numbers = numbers.replace(",,", ",")
        val stringBuilder = StringBuilder(numbers)
        if (numbers[0] == ',') {
            numbers = stringBuilder.deleteAt(0).toString()
        }
        if (numbers[numbers.length-1] == ',') {
            numbers = stringBuilder.deleteAt(numbers.length-1).toString()
        }
        return numbers.split(",").map { it.toInt() }.toIntArray()
    }

    fun buttonAction(view: View) {
        if (binding.numbers.text.toString() != "") {
            if (binding.bubbleSortRadioBtn.isChecked) {
                printResult("Bubble Sort", bubbleSortAction(view))
            } else if (binding.quickSortRadioBtn.isChecked) {
                printResult("Quick Sort", quickSortAction(view))
            }
        } else {
            val message = "Type your numbers first"
            binding.result.text = message
            binding.result.visibility = View.VISIBLE
        }
    }

    private fun bubbleSortAction(view: View): IntArray {
        val bubbleSort = BubbleSort(getNumbers())
        bubbleSort.bubbleSort()
        return bubbleSort.getArray()
    }

    private fun quickSortAction(view: View): IntArray {
        val quickSort = QuickSort(getNumbers())
        quickSort.sort()
        return quickSort.getArray()
    }

    private fun printResult(method: String, numbers: IntArray) {
        val result = binding.result
        var resultMessage = "Sorted using $method Method. \nSorted list: "
        for (i in numbers.indices) {
            resultMessage += if (i == numbers.size-1) {
                numbers[i].toString()
            } else {
                numbers[i].toString() + ", "
            }
        }
        result.text = resultMessage
        result.visibility = View.VISIBLE
    }
}