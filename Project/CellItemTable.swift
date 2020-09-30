//
//  CellItemTable.swift
//  Project
//
//  Created by Majed on 12/12/19.
//  Copyright © 2019 Majed. All rights reserved.
//

import Foundation
class CellItemTable{
    var Image:String?
    var title:String?
    var date:String?
    var Price:String?
    
    init(Image:String, title:String, date:String, Price:String) {
        self.Image = Image
        self.title = title
        self.date = date
        self.Price = Price
    }

}
