//
//  TableViewCell.swift
//  Project
//
//  Created by Majed on 12/13/19.
//  Copyright © 2019 Majed. All rights reserved.
//

import UIKit

class TableViewCell: UITableViewCell {

    @IBOutlet weak var laTableImage: UIImageView!
    @IBOutlet weak var laTitle: UILabel!
    @IBOutlet weak var laDate: UILabel!
    @IBOutlet weak var laPrice: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
