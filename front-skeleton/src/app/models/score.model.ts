export interface Score {
  id?: number;
  userId: number;
  quizId: number;
  score: number;
  timeTaken: number;    // en secondes
}
