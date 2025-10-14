
export interface Quiz {
  id: number;
  title: string;
  category: string;
  description: string;
  level: 'Facile' | 'Moyen' | 'Difficile';
  players: number;
  questions: any[];
  timeLimitPerQuestion?: number; // temps par question
  createdBy?: string;
}
