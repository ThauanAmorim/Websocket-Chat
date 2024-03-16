import { Component, Input, OnInit } from '@angular/core';
import { SharedComponentService } from 'src/app/shared/service/shared-component-service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  @Input() name: string;
  @Input() idChat: number;

  constructor(
      private sharedComponentService: SharedComponentService
  ) {
    this.name = '';
    this.idChat = 0;
  }

  ngOnInit(): void {}

  loadChat() {
    if (this.sharedComponentService.chatPanel) {
      this.sharedComponentService.chatPanel.canRender = true;
    }

    this.sharedComponentService.chatPanel?.connect(this.idChat);
  }

}
