//
//  ViewController.swift
//  Magic 8 ball
//
//  Created by 유다훈 on 2018. 7. 17..
//  Copyright © 2018년 PandaYoo. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    var ballArray = ["ball1", "ball2", "ball3", "ball4", "ball5"];
    var randomBallNumber : Int = 0;
    
    @IBOutlet weak var imageView: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        newBallImage();
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func askButtonPressed(_ sender: UIButton) {
        newBallImage();
        
    }
    override func motionEnded(_ motion: UIEventSubtype, with event: UIEvent?) {
        newBallImage();
    }
    
    func newBallImage() {
        randomBallNumber = Int(arc4random_uniform(5));
        imageView.image = UIImage(named: ballArray[randomBallNumber]);
    }
    
}

