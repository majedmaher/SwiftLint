//
//  CellItemTable.swift
//  Project
//
//  Created by Majed on 12/12/19.
//  Copyright © 2019 Majed. All rights reserved.
//

import Foundation
class CellItemTable{
    var image: String?
    var title: String?
    var date: String?
    var price: String?
    
    init(image:String, title:String, date:String, price:String) {
        self.image = image
        self.title = title
        self.date = date
        self.price = price
    }

}
