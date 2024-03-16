import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { ChatMinResponse } from "../interface/chat-min-response";
import { BASE_URL } from "src/environments/environment";
import { Response } from "../interface/response/response";

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private readonly CHAT_PATH = 'chat';

  constructor(private httpClient: HttpClient) { }

  findAll() {
    return this.httpClient.get<Response<ChatMinResponse[]>>(BASE_URL.concat(this.CHAT_PATH));
  }

}
