import { Injectable } from "@angular/core";
import { ChatPanelComponent } from "src/app/components/chat-panel/chat-panel.component";

@Injectable({
  providedIn: 'root'
})
export class SharedComponentService {

  private _chatPanel: ChatPanelComponent | undefined;

  constructor() {
    this._chatPanel = undefined;
  }

  public set chatPanel(chatPanel : ChatPanelComponent | undefined) {
    this._chatPanel = chatPanel;
  }

  public get chatPanel() : ChatPanelComponent | undefined {
    return this._chatPanel;
  }

}
