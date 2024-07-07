import { registerPlugin } from '@capacitor/core';

import type { SslSkipPlugin } from './definitions';

const SslSkip = registerPlugin<SslSkipPlugin>('SslSkip', {});

export * from './definitions';
export { SslSkip };
