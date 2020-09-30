//
//  CellItem.swift
//  Project
//
//  Created by Majed on 12/6/19.
//  Copyright © 2019 Majed. All rights reserved.
//

import Foundation
class CellItem {
    var image: String?
    var name: String?
    var shortcut: String?
    var price: String?
    var profir: String?

    init(image: String, name: String, shortcut: String, price: String, profir: String) {
        self.image = image
        self.name = name
        self.shortcut = shortcut
        self.price = price
        self.profir = profir
    }
}
