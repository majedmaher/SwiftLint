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

    var CellList = Array<CellItem>()
    
    var CellTableList = Array<CellItemTable>()

    @IBOutlet weak var CollectionViewList: UICollectionView!

    @IBOutlet weak var TableViewList: UITableView!

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        navigationController?.setNavigationBarHidden(true, animated: true)
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        self.CollectionViewList?.backgroundColor = UIColor.clear

        //self.navigationController?.navigationBar.barTintColor = UIColor.white
        //[[UINavigationBar appearance] setBarTintColor:[UIColor yellowColor]];

        CellList.append(CellItem(image: "BTC", name: "Bit Coin", shortcut: "BTC",
                                 price: "₹359,536.28", profir: "-0.45"))

        CellList.append(CellItem(image: "ETH", name: "Ethereum", shortcut: "ETH",
                                 price: "₹10,699.58", profir: "+0.56"))

        CellList.append(CellItem(image: "BTH", name: "Ethereum", shortcut: "BTC",
                                 price: "₹10,699.58", profir: "+0.56"))

        CellTableList.append(CellItemTable.init(Image: "BTC", title: "Bit Coin",
                                                date: "Yesterday", Price: "₹56,293"))

        CellTableList.append(CellItemTable.init(Image: "ETH", title: "Ethereum",
                                                date: "14:20 12 Apr", Price: "₹10,000"))

        CellTableList.append(CellItemTable.init(Image: "BTC", title: "Bit Coin",
                                                date: "13:39 16 July", Price: "₹4,500"))

    }

    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }

    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return CellList.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell:CollectionViewCell=collectionView.dequeueReusableCell(withReuseIdentifier: "cell", for: indexPath) as! CollectionViewCell
        cell.laImage.image = UIImage(named: CellList[indexPath.row].image!)
        cell.laName.text = CellList[indexPath.row].name!
        cell.laShortcut.text = CellList[indexPath.row].shortcut!
        cell.laPrice.text = CellList[indexPath.row].price!
        cell.laProfit.text = CellList[indexPath.row].profir!

        let Profir = Double(CellList[indexPath.row].profir!) ?? 0

        if(Profir >= 0.00) {
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
        return CellTableList.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

        let cell = tableView.dequeueReusableCell(withIdentifier: "tableViewCell", for: indexPath) as! TableViewCell

        cell.laTableImage.image = UIImage(named: self.CellTableList[indexPath.row].Image!)
        cell.laTitle.text = CellTableList[indexPath.row].title!
        cell.laDate.text = CellTableList[indexPath.row].date!
        cell.laPrice.text = CellTableList[indexPath.row].Price!

        return cell
    }

    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 100
    }

}
