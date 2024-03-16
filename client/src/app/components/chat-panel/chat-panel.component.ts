import { Component, OnInit } from '@angular/core';
import { MessageRequest } from 'src/app/shared/interface/message-request';
import { MessageResponse } from 'src/app/shared/interface/message-response';
import { ChatWebsocketService } from 'src/app/shared/service/chat-websocket-service';
import { SharedComponentService } from 'src/app/shared/service/shared-component-service';

@Component({
  selector: 'app-chat-panel',
  templateUrl: './chat-panel.component.html',
  styleUrls: ['./chat-panel.component.css']
})
export class ChatPanelComponent implements OnInit {

  canRender: boolean = false;

  private idChat: number;
  messages: MessageResponse[] = [];

  constructor(
    private chatWebsocketService: ChatWebsocketService,
    private sharedComponentService: SharedComponentService
  ) {
    this.idChat = 0;
  }

  ngOnInit(): void {
    this.sharedComponentService.chatPanel = this;
  }

  connect(idChat: number) {
    this.idChat = idChat;
    this.messages = [];
    this.chatWebsocketService.socket.unsubscribe();
    this.chatWebsocketService.createConnection(idChat);
    this.createWebsocketSubscribe();
  }

  sendMessage(message: HTMLInputElement, event: Event): void {
    event.preventDefault();

    if (!message.value || message.value.trim().length == 0) {
      return;
    }

    const senderId = sessionStorage.getItem("senderId");
    const messageRequest: MessageRequest = {
      chatId: this.idChat,
      senderId: Number.parseInt(senderId ?? "0"),
      content: message.value
    };
    message.value = "";

    this.chatWebsocketService.sendMessage(messageRequest);
  }

  createWebsocketSubscribe() {
    this.chatWebsocketService.socket.subscribe(
      (response) => this.addMessage(response as MessageResponse),
      (error) => console.error('Error:', error),
      () => { }
    );
  }

  addMessage(messageResponse: MessageResponse) {
    this.messages.unshift(messageResponse);
  }
}
