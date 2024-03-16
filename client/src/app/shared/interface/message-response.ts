import { UserResponse } from "./user-response";

export interface MessageResponse {
  content: string,
  sender: UserResponse
}
