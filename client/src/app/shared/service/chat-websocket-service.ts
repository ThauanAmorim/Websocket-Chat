import { Injectable } from "@angular/core";
import { MessageRequest } from "../interface/message-request";
import { WebSocketSubject, webSocket } from 'rxjs/webSocket';

@Injectable({
  providedIn: 'root'
})
export class ChatWebsocketService {

  private _socket = webSocket('ws://localhost:8080/websocket')

  createConnection(chatId: number) {
    this._socket = webSocket(`ws://localhost:8080/websocket?chatId=${chatId}`);
  }

  public get socket() : WebSocketSubject<unknown> {
    return this._socket;
  }


  sendMessage(messageRequest: MessageRequest) {
    this.socket.next(messageRequest);
  }

}
