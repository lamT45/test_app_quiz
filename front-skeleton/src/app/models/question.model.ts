export interface Question {
  id?: number;
  text: string;
  type: 'Vrai/Faux' | 'Quiz';
  points: number;
  choices: {
    text: string;
    correct: boolean;
  }[];
  quizId: number;
  imageUrl?: string;
}
