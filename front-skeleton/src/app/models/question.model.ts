export interface Question {
  id?: number;
  quizId: number;
  text: string;
  type: 'Vrai/Faux' | 'Quiz';
  points: number;
  choices: {
    text: string;
    correct: boolean;
  }[];
  imageUrl?: string;
}
