export interface Score {
  id?: number;
  score_obtained: number;
  time_taken_seconds: number;
  user?: {
    id: number;
    username?: string;
    email?: string;
  };
  quiz?: {
    id: number;
    title?: string;
  };
}
