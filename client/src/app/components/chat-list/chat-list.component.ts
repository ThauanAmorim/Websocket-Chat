import { Component, OnInit } from '@angular/core';
import { ChatMinResponse } from 'src/app/shared/interface/chat-min-response';
import { ChatService } from 'src/app/shared/service/chat-service';

@Component({
  selector: 'app-chat-list',
  templateUrl: './chat-list.component.html',
  styleUrls: ['./chat-list.component.css']
})
export class ChatListComponent implements OnInit {

  chats: ChatMinResponse[] = [];

  constructor(private chatService: ChatService) {}

  ngOnInit(): void {
    this.loadChats();
  }

  loadChats() {
    this.chatService.findAll().subscribe({
      next: (next) => {
        this.chats = next.data;
      }
    });
  }

}
