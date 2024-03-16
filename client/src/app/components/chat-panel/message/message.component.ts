import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  @Input() userLogin: string;
  @Input() content: string;

  constructor() {
    this.userLogin = "";
    this.content = "";
  }

  ngOnInit(): void {}

}
