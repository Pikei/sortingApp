package com.sortingapp.androidsample

class QuickSort(private var arr: IntArray) {
    fun sort() {
        val start = 0
        val end = arr.size-1
        quickSort(start, end)
    }

    private fun partition(start: Int, end: Int): Int {
        val pivotIndex = pivotIndex(start, end)
        val pivot = arr[pivotIndex]
        swap(pivotIndex, end)
        var l: Int
        var r: Int
        do {
            l = leftIndex(start, end, pivot)
            r = rightIndex(start, end, pivot)

            if (l < r) {
                swap(l, r)
            } else {
                swap(l, end)
            }
        } while (l <= r && r - l != 1)
        return l
    }

    private fun leftIndex(start: Int, end: Int, pivot: Int): Int {
        for (i in start until end) {
            if (arr[i] > pivot) {
                return i
            }
        }
        return 0
    }

    private fun rightIndex(start: Int, end: Int, pivot: Int): Int {
        for (i in end downTo start) {
            if (arr[i] < pivot) {
                return i
            }
        }
        return 0
    }

    private fun swap(i1: Int, i2: Int) {
        val temp = arr[i1]
        arr[i1] = arr[i2]
        arr[i2] = temp
    }

    private fun quickSort(start: Int, end: Int) {
        if(start < end) {
            val i = partition(start, end)
            quickSort(start, i-1)
            quickSort(i+1, end)
        }
    }
    private fun pivotIndex(start: Int, end: Int): Int {
        return (start + end) / 2
    }

    fun getArray(): IntArray {
        return this.arr
    }
}