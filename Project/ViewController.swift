//
//  ViewController.swift
//  Project
//
//  Created by Majed on 12/5/19.
//  Copyright © 2019 Majed. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UICollectionViewDelegate, UICollectionViewDataSource,
UITableViewDelegate, UITableViewDataSource {

    var cellList = Array<CellItem>()
    
    var cellTableList = Array<CellItemTable>()

    @IBOutlet weak var collectionViewList: UICollectionView!

    @IBOutlet weak var tableViewList: UITableView!

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        navigationController?.setNavigationBarHidden(true, animated: true)
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        self.collectionViewList?.backgroundColor = UIColor.clear

        //self.navigationController?.navigationBar.barTintColor = UIColor.white
        //[[UINavigationBar appearance] setBarTintColor:[UIColor yellowColor]];

        cellList.append(CellItem(image: "BTC", name: "Bit Coin", shortcut: "BTC",
                                 price: "₹359,536.28", profir: "-0.45"))

        cellList.append(CellItem(image: "ETH", name: "Ethereum", shortcut: "ETH",
                                 price: "₹10,699.58", profir: "+0.56"))

        cellList.append(CellItem(image: "BTH", name: "Ethereum", shortcut: "BTC",
                                 price: "₹10,699.58", profir: "+0.56"))

        cellTableList.append(CellItemTable.init(image: "BTC", title: "Bit Coin",
                                                date: "Yesterday", price: "₹56,293"))

        cellTableList.append(CellItemTable.init(image: "ETH", title: "Ethereum",
                                                date: "14:20 12 Apr", price: "₹10,000"))

        cellTableList.append(CellItemTable.init(image: "BTC", title: "Bit Coin",
                                                date: "13:39 16 July", price: "₹4,500"))

    }

    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }

    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return cellList.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell:CollectionViewCell=(collectionView.dequeueReusableCell(withReuseIdentifier: "cell", for: indexPath) as? CollectionViewCell)!
        cell.laImage.image = UIImage(named: cellList[indexPath.row].image!)
        cell.laName.text = cellList[indexPath.row].name!
        cell.laShortcut.text = cellList[indexPath.row].shortcut!
        cell.laPrice.text = cellList[indexPath.row].price!
        cell.laProfit.text = cellList[indexPath.row].profir!

        let profir = Double(cellList[indexPath.row].profir!) ?? 0

        if(profir >= 0.00) {
            cell.laProfit.textColor = .green
        } else {
            cell.laProfit.textColor = .red
        }

        return cell
    }

    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return cellTableList.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

        let cell = tableView.dequeueReusableCell(withIdentifier: "tableViewCell", for: indexPath) as? TableViewCell

        cell?.laTableImage.image = UIImage(named: self.cellTableList[indexPath.row].image!)
        cell!.laTitle.text = cellTableList[indexPath.row].title!
        cell!.laDate.text = cellTableList[indexPath.row].date!
        cell!.laPrice.text = cellTableList[indexPath.row].price!

        return cell!
    }

    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 100
    }

}
