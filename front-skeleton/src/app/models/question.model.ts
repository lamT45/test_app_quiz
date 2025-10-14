export interface Question {
  id?: number;
  quizId: number;
  text: string;
  type: 'single' | 'multiple';
  points: number;
  choices: {
    text: string;
    correct: boolean;
  }[];
  imageUrl?: string;
}
