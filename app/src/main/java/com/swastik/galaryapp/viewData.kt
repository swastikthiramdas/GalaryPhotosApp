package com.swastik.galaryapp

import android.media.Image

class viewData {

    var image: String? = null
    var image_name: String? = null

    constructor(image: String? ,image_name : String?)  {

        this.image = image
        this.image_name = image_name
    }
    constructor()
}

