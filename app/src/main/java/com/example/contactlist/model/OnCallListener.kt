package com.example.contactlist.model

interface OnCallListener<T> {

    fun onCall(t: T)

    fun onMessage(t: T)
}