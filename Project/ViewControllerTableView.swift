//
//  ViewControllerTableView.swift
//  Project
//
//  Created by Majed on 12/13/19.
//  Copyright © 2019 Majed. All rights reserved.
//

import UIKit

class ViewControllerTableView: UIViewController, UITableViewDelegate, UITableViewDataSource {

    var cellTableList = Array<CellItemTable>()

    @IBOutlet weak var tableViewList: UITableView!
    @IBOutlet weak var laPurchased: UILabel!
    @IBOutlet weak var laSold: UILabel!
    @IBOutlet weak var laEarned: UILabel!
    @IBOutlet weak var laLost: UILabel!

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)

        navigationController?.setNavigationBarHidden(false, animated: false)

        let backBTN = UIBarButtonItem(image: UIImage(named: "Group 55"),
                                      style: .plain,
                                      target: navigationController,
                                      action: #selector(UINavigationController.popViewController(animated:)))
        navigationItem.leftBarButtonItem = backBTN
        navigationController?.interactivePopGestureRecognizer?.delegate = self as? UIGestureRecognizerDelegate

        navigationItem.leftBarButtonItem?.tintColor = UIColor.gray

        self.navigationController?.navigationBar.setValue(true, forKey: "hidesShadow")

    }

    override func viewDidLoad() {
        super.viewDidLoad()

        do {
            let url = "https://scadagame.com/bitCuckoo"
            guard let appURL = URL(string: url) else { return  }
            let data = try? Data(contentsOf: appURL)
            let json = try? JSONSerialization.jsonObject(with: data!) as? [String:Any]
            let result = json!["result"] as? [String:Any]
            let transactions = result!["transactions"] as? [[String:Any]]

            self.laPurchased.text = "\(result!["you_purchased"]!)"
            self.laSold.text = "\(result!["you_sold"]!)"
            self.laEarned.text = "\(result!["you_earned"]!)"
            self.laLost.text = "\(result!["you_lost"]!)"

            DispatchQueue.global().sync {

                for item in transactions! {

                let title = item["title"] as? String
                let date = item["date"] as? String
                let price = "₹\(item["price"]! as? Int)"

                    if (title!.elementsEqual("Bit Coin")) {
                        self.cellTableList.append(CellItemTable.init(image: "BTC", title: title!, date: date!, price: price))
                    }else if(title!.elementsEqual("Ethereum")) {
                        self.cellTableList.append(CellItemTable.init(image: "ETH", title: title!, date: date!, price: price))
                }

            }
            }
        }
    }

    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return cellTableList.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

        let cell = tableView.dequeueReusableCell(withIdentifier: "tableCell", for: indexPath) as? TableViewCell

        cell!.laTableImage.image = UIImage(named: cellTableList[indexPath.row].image!)
        cell!.laTitle.text = cellTableList[indexPath.row].title!
        cell!.laDate.text = cellTableList[indexPath.row].date!
        cell!.laPrice.text = cellTableList[indexPath.row].price!

        return cell!
    }

    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return "Transaction History"
    }

    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 100
    }
}
